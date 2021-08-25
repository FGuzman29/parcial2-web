#!/usr/bin/env python

from suds.client import Client
from os import system, name

user = 'anonimo'
enlacesAnonimo = []    
url = "http://localhost:7000/ws/SoapWebServiceService?wsdl"    
cliente = Client(url)


def main():
    urls = ['https://www.npmjs.com/package/soap','https://github.com/']
    for i in urls:
        res = cliente.service.registrarEnlace(i,'admin')
        print(str(res.URL))    
    #consultas(cliente)

def consultas(cliente):
    menu1 = '\nCliente de Acortador SOAP\n 1 - Iniciar Sesion\n 2- Continuar como usuario anonimo(Los usuarios anonimos no podran visualizar las url creadas una vez terminado el programa) \n 3- Salir'
    res = 0

    while(res != 3):
        print(menu1)
        res = int(input('\nIngrese la opcion deseada: '))

        if res > 0 and res < 4:
            if res == 1:
                autentificar(cliente)
            if res == 2:
               masOpciones(cliente)

def autentificar(cliente):
    aux = True
    while(aux):
        print('\n\n==== Log-in ====\n')

        usuario = input('Ingrese su nombre de usuario:')
        password = input('Ingrese su contraseña: ')

        if(cliente.service.autentificacion(usuario,password)):
            user = usuario
            masOpciones(cliente)
            return
        else:
            res = int(input('\nUsuario o Contraseña incorrectos!\n 1 - Reintentar\n 2 - Volver al menu anterior\nSelecione una opcion: '))

            if(res < 1 or res > 2):
                print('\nOpcion Invalida! Intente denuevo\n')
            elif(res == 2):
                masOpciones(cliente)
                return

def masOpciones(cliente):
    res = 0

    while(res != 4):
        res = int(input("Opciones:\n 1 - Acortar enlace\n 2 - Listar Enlaces\n 3 - Cerrar Sesion\n 4 - Salir"))

        if(res > 0 and res < 5):
            if(res == 1):
                acortar(cliente)
            #if(res == 2):
             #   listar(cliente)
            if(res == 3):
                user = 'anonimo'

            return
        else:
            print('\n\nOpcion Invalida! Intente denuevo')
def acortar(cliente):
    aux = True
    while aux:
        url = input('\nIngrese la URL que desea acortar: ')
        res = cliente.service.registrarEnlace(url)

        if(res == None):
            print('\nError! Por favor revise la URL')
        else:
            print('\n\n' + str(res))



main()

