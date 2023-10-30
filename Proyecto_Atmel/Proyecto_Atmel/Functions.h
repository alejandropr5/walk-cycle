/*
 * Functions.h
 *
 * Created: 1/11/2021 11:32:28 a. m.
 *  Author: alejandro
 */ 


#ifndef FUNCTIONS_H_
#define FUNCTIONS_H_

// C program for functions implementation
#include <math.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <avr/interrupt.h>

// Reverses a string 'str' of length 'len'
void reverse(char* str, int len)
{
	int i = 0, j = len - 1, temp;
	while (i < j) {
		temp = str[i];
		str[i] = str[j];
		str[j] = temp;
		i++;
		j--;
	}
}

// Converts a given integer x to string str[].
// d is the number of digits required in the output.
// If d is more than the number of digits in x,
// then 0s are added at the beginning.
int intToStr(int x, char str[], int d)
{
	int i = 0;
	while (x) {
		str[i++] = (x % 10) + '0';
		x = x / 10;
	}
	
	// If number of digits required is more, then
	// add 0s at the beginning
	while (i < d)
	str[i++] = '0';
	
	reverse(str, i);
	str[i] = '\0';
	return i;
}

// Converts a floating-point/double number to a string.
void ftoa(float n, char* res, int afterpoint)
{
	// Extract integer part
	int ipart = (int)n;
	
	// Extract floating part
	float fpart = n - (float)ipart;
	
	// convert integer part to string
	int i = intToStr(ipart, res, 0);
	
	// check for display option after point
	if (afterpoint != 0) {
		res[i] = '.'; // add dot
		
		// Get the value of fraction part upto given no.
		// of points after dot. The third parameter
		// is needed to handle cases like 233.007
		fpart = fpart * pow(10, afterpoint);
		
		intToStr((int)fpart, res + i + 1, afterpoint);
	}
}

void Config_ADC(void)
{
	/*	Registro ADMUX
		Analog Ref será externo (AVcc), Resultado ADC con ajuste a la 
		izquierda (8 bits), entrada análoga ADC0 (A0) */
	ADMUX = (0<<REFS1)|(1<<REFS0)|(0<<ADLAR)|(0<<MUX3)|(0<<MUX2)|(0<<MUX1)|(0<<MUX0);
	
	/*	Registro ADCSRA
		El ADC estará habilitado (esto es diferente a que inicie conversión ADSC), 
		sin auto-triggered, bandera en cero e interrupcion deshabilitada. El prescaler 
		en 128 para que con el XTAL de 16 MHz el clock del ADC sea 125 kHz
		Tener en cuenta que Clock del ADC debe estar entre 50 kHz y 200 kHz */
	ADCSRA = (1<<ADEN)|(0<<ADSC)|(0<<ADATE)|(0<<ADIF)|(0<<ADIE)|(1<<ADPS2)|(1<<ADPS1)|(1<<ADPS0);
	
	/*	Registro ADCSRB
		El mux analogo encendido (ACME=0) y la fuente de Trigger no tiene efecto porque
		no esta habilitado el modo auto-trigger (ADATE=0) */
	ADCSRB = (0<<ACME)|(0<<ADTS2)|(0<<ADTS1)|(0<<ADTS0);
	
	/*	Registro DIDR0
		apago el buffer digital del pin ADC4 para disminuir consumo de energía
		y no tener en cuenta este pin como entrada digital */
	DIDR0 = (0<<ADC5D)|(0<<ADC4D)|(0<<ADC3D)|(0<<ADC2D)|(1<<ADC1D)|(1<<ADC0D);
}

void USART_Config(void)
{
	UCSR0A = (0<<RXC0)|(0<<TXC0)|(0<<UDRE0)|(0<<FE0)|(0<<DOR0)|(0<<UPE0)|(0<<U2X0)|(0<<MPCM0);
	
	/* Registro UCSR0B configurado para que la USART funcione realmente como UART (modo asíncrono)
	habilitando Rx y Tx, Interrupción para RX. Velocidad normal */
	UCSR0B = (1<<RXCIE0)|(0<<TXCIE0)|(0<<UDRIE0)|(1<<RXEN0)|(1<<TXEN0)|(0<<UCSZ02)|(0<<RXB80)|(0<<TXB80);
	
	/* Registro UCSR0C configurado para modo asíncrono, modo paridad deshabilitado, 1 bit de stop 
	y 8 bits de datos */
	UCSR0C = (0<<UMSEL01)|(0<<UMSEL00)|(0<<UPM01)|(0<<UPM00)|(0<<USBS0)|(1<<UCSZ01)|(1<<UCSZ00)|(0<<UCPOL0);
	
	UBRR0 = 103;  // Velocidad de 9600 bps con XTAL de 16 MHz
}

float Leer_Puerto_Analogo(uint8_t ADCn)
{
	ADMUX &=~ 0x0F;				
	ADMUX |=  ADCn;	//Entrada análoga ADCn 
		
	ADCSRA |= (1<<ADSC);		//Inicio la conversión de un dato
		
	while (ADCSRA & (1<<ADSC)); /*El bit ADSC permanece en "1" mientras la conversión sucede. Luego 
								de que la conversión A/D finaliza, este bit es automáticamente reseteado*/
	
	float conv = ADC;			//Obtener el valor análogo convertido
			
	return (conv/1024)*5;		//Retornar el valor de la tensión	
}

float Obtener_GX(float vx, uint8_t nSensor){
	float offset =0;
	float sensitivity =0;
	
	switch(nSensor){
		case 1:
		offset =	1.7069;
		sensitivity = 0.3394;
		break;
		case 2:
		offset = 1.6902;
		sensitivity = 0.3442;
		break;
	}
	
	return (vx-offset)/sensitivity;
}

float Obtener_GY(float vy, uint8_t nSensor){
	float offset =0;
	float sensitivity =0;
	
	switch(nSensor){
		case 1:
		offset =	1.6939;
		sensitivity = 0.3513;
		break;
		case 2:
		offset = 1.7003;
		sensitivity = 0.3432;
		break;
	}
	
	return (vy-offset)/sensitivity;
}

float Definir_Rango(float g)
{
	if(g>1)			g=1;
	else if(g<-1)	g=-1;
	return g;
}

float Obtener_Angulo(float vx, float vy, uint8_t nSensor)
{
	float gx= Obtener_GX(vx, nSensor);	//Obtener la magnitud y sentido de la aceleración en el eje  de sensibilidad x
	float gy= Obtener_GY(vy,nSensor);	//Obtener la magnitud y sentido de la aceleración en el eje  de sensibilidad y
		
	gx= Definir_Rango(gx);
	gy= Definir_Rango(gy);
		
	float beta=1;
	
	uint8_t op =0;
	if(gx>=0	&&	gy>=0)	op=1;	//Quadrant I
	if(gx<0		&&	gy>=0)	op=2;	//Quadrant II
	if(gx<0		&&	gy<0)	op=3;	//Quadrant III
	if(gx>=0	&&	gy<0)	op=4;	//Quadrant IV
	
	switch (op)
	{
		case 1:
		beta = (asin(gx/1) * (180.0 / M_PI)) +90; 
		break;
		case 2:
		beta = (acos(fabs(gx)/1) * (180.0 / M_PI));
		break;
		case 3:
		beta = asin(fabs(gx)/1) * (180.0 / M_PI) +270;
		break;
		case 4:
		beta = acos(gx/1) * (180.0 / M_PI) +180;
		break;
	}
	
	return beta;	
}

void Imprimir_str(char* str)
{
	size_t len = strlen(str);
	char flag=0;
	for (unsigned char i=0; i<len;i++)
	{
		if(flag==0)
		{
			flag=1;
			if(str[i]==46)
			{
				UDR0 = 48;		//Agregar "0" antes del punto
				_delay_ms(1);
			}
		}
		if(str[i]==0)
		{
			str[i]=32;  // si el string es "null" se reemplaza por un "space"
		}
		UDR0 = str[i];
		_delay_ms(1);
	}
	_delay_ms(1);
}

float Angulo_Pierna(float Bi, float Bs)
{	
	return fabs(Bs-Bi);
}

char* definir_Estado(char state[3], float aRod)
{		
	uint8_t n = 0;
	if(!strcmp(state, "CI") && aRod>=8)		n=1;	
	if(!strcmp(state, "AI") && aRod<=9)		n=2;	
	if(!strcmp(state, "AM") && aRod>=9)		n=3;
	if(!strcmp(state, "AF") && aRod>=21)	n=4;
	if(!strcmp(state, "OP") && aRod>=40)	n=5;
	if(!strcmp(state, "OI") && aRod<=21)	n=6;
	if(!strcmp(state, "OM") && aRod<=7)		n=7;	
	if(!strcmp(state, "OF"))				n=8;
	
	switch (n)
	{
		case 1:
		state = "AI";
		break;
		case 2:
		state = "AM";
		break;
		case 3:
		state = "AF";
		break;
		case 4:
		state = "OP";
		break;
		case 5:
		state = "OI";
		break;
		case 6:
		state = "OM";
		break;
		case 7:
		state = "OF";
		break;
		case 8:
		state = "CI";
		break;
	}
	
	return state;
}

void salto_de_linea()
{
	UDR0 = 13;	//Enviar caracter CR
	_delay_ms(1);
	UDR0 = 10;	//Enviar caracter LF
	_delay_ms(1);
}

void Imprimir_Float(float n, char* str_arr, int afterpoint)
{
	ftoa(n, str_arr, afterpoint);
	Imprimir_str(str_arr);
}

unsigned int TCNT(uint8_t Fsampling)
{
	return 65535-(16000000/(256*Fsampling));
}

void Config_Timer1(uint8_t Fsampling)
{
	TCCR1A	= 0x00;				//Configurar en modo normal
	TIFR1	= 0x00;				//Clarear bandera de Timer1
	TCNT1	= TCNT(Fsampling);	//Valor de TCNT vara un periodo de	500ms
	TIMSK1	= (1<<TOIE1);		//Habilitar interrupción por desbordamiento
}


#endif /* FUNCTIONS_H_ */