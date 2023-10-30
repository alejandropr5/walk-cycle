/*
 * Proyecto_Atmel.c
 *
 * Created: 6/11/2021 4:41:59 p. m.
 * Author : alejandro
 */ 

#define F_CPU 16000000L
#include <avr/io.h>
#include <util/delay.h>
#include "Functions.h"

char str_arr[7];
float vx,vy,Bs,Bi,aRod; 
char* state = "CI";
char datoRx;
uint8_t Timer1_OVF_Flag=0, USART_RX_Flag=0;
uint8_t fsampling	=	8;	//Definir valor de de frecuencia de sampleo

int main(void)
{	
	//Salidas
	DDRB	=	(1<<PB5);	//Configurar pin PB5 como salida
	
	//Configuraciones iniciales	
	Config_ADC();
	USART_Config();
	Config_Timer1(fsampling);
	
	sei();					//Habilitar interrupciones globales
	
    while (1) 
    {
		if(Timer1_OVF_Flag==1)
		{
			Timer1_OVF_Flag=0;
			//ACELERÓMETRO DEL SEGMENTO SUPERIOR		
			vx = Leer_Puerto_Analogo(0);
					
			vy = Leer_Puerto_Analogo(1);
		
			Bs= Obtener_Angulo(vx,vy, 2);
		
			//ACELERÓMETRO DEL SEGMENTO INFERIOR		
			vx = Leer_Puerto_Analogo(2);
		
			vy = Leer_Puerto_Analogo(3);
		
			Bi= Obtener_Angulo(vx,vy, 1);	
		
			aRod = Angulo_Pierna(Bi,Bs);
			state = definir_Estado(state, aRod);
			
			//Enviar datos en formato Json
			Imprimir_str("{\"aRod\": ");
			Imprimir_Float(aRod,str_arr,2);
			Imprimir_str(", \"fase\": ");		
			Imprimir_str(state);
			Imprimir_str("}");					
			
			salto_de_linea();	
		}
    }
}

ISR(TIMER1_OVF_vect)
{
	TCNT1	= TCNT(fsampling);	//Recargar valor del Timer1
	Timer1_OVF_Flag=1;
}

ISR(USART_RX_vect)
{
	datoRx = UDR0;
	_delay_ms(1);
	
	if(datoRx==49) USART_RX_Flag = 1;
	if(datoRx==50) USART_RX_Flag = 0;
	
	if(USART_RX_Flag==1){
		TCCR1B |= (1<<CS12);
		PORTB |= (1<<PB5);
	}else{
		TCCR1B &=~ (1<<CS12);
		PORTB &=~ (1<<PB5);
	}
}

