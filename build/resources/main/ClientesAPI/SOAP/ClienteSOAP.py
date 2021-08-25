#!/usr/bin/env python

from suds.client import Client

user = 'anonimo'
enlacesAnonimo = []    
url = "http://localhost:7000/ws/SoapWebServiceService?wsdl"    
cliente = Client(url)



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
    global user
    while(aux):
        print('\n\n==== Log-in ====\n')

        us = input('Ingrese su nombre de usuario: ')
        pas = input('Ingrese su contraseña: ')
        res = cliente.service.autentificacion(us, pas)

        if(res):
            user = us
            masOpciones(cliente)
            return
        else:
            res = int(input('\nUsuario o Contraseña incorrectos!\n 1 - Reintentar\n 2 - Ingresar como anonimo\nSelecione una opcion: '))

            if(res < 1 or res > 2):
                print('\nOpcion Invalida! Intente denuevo\n')
            elif(res == 2):
                masOpciones(cliente)
                return

def masOpciones(cliente):
    res = 0
    global user 
    global enlacesAnonimo
    while(res != 4):
        res = int(input("\nOpciones:\n 1 - Acortar enlace\n 2 - Listar Enlaces\n 3 - Visualizar un Enlace por ID\n 4 - Cerrar Sesion\n 5 - Salir\nSelecione una opcion: "))

        if(res > 0 and res < 6):
            if(res == 1):
                acortar(cliente)
            if(res == 2):
                listar(cliente)
            if(res == 3):
                mostrar(cliente)
            if(res == 4):
                user = 'anonimo'
                enlacesAnonimo = []
            if(res == 5):
                exit()
        else:
            print('\n\nOpcion Invalida! Intente denuevo')

def acortar(cliente):
    url = input('\nIngrese la URL que desea acortar: ')
    res = cliente.service.registrarEnlace(url,user)
    
    if(res == None):
        print('\nError! Por favor revise la URL')
    else:
        print('\n\n===================Resultado===================')
        print("ID: " + str(res.idEnlace))
        print('URL Original: '+  res.URL)
        print('URL Acortada: ' + res.URLAcostarda)
        print('Fecha de creacion: '+ str(res.fecha))
        print('Foto en Base64: '+ res.fotoBase64[0:20]+'...')
        print('===================================================\n\n')

    if user == 'anonimo':
        enlacesAnonimo.append(res)

def listar(cliente):
    if user == 'anonimo':
        res = enlacesAnonimo
    else:
        res = cliente.service.getEnlaces(user)

    print('===================Resultado===================')

    for enlace in res:
        print("ID: " + str(enlace.idEnlace))
        print('URL Original: '+  enlace.URL)
        print('URL Acortada: ' + enlace.URLAcostarda)
        print('Fecha de creacion: '+ str(enlace.fecha))
        print('Cantidad de veces accedidas: '+ str(enlace.vecesAccesidas))
        print('Foto en Base64: '+ enlace.fotoBase64[0:20]+'...')
        print('===================================================')

def mostrar(cliente):
    id = int(input('\nIngrese el ID del enlace que desea visualizar: '))
    if(id > 0):
        res = cliente.service.getEnlace(id,user)
        print('\n\n===================Resultado===================\n')
        print("ID: " + str(res.idEnlace))
        print('URL Original: '+  res.URL)
        print('URL Acortada: ' + res.URLAcostarda)
        print('Fecha de creacion: '+ str(res.fecha))
        print('Cantidad de veces accedidas: '+ str(res.vecesAccesidas))
        print('Foto en Base64: '+ res.fotoBase64[0:20]+'...')
        print('\n=======================Estadisticas=======================\n')

        clientes = cliente.service.getClientes(res.idEnlace)
        if res.vecesAccesidas > 0:
            print('|     IP        |    Navegador   y   Sistema  |   Fecha')
            for cliente in clientes:
               print('  '+cliente.ip + '  |     ' + cliente.navegador+ '   -   '+cliente.sistema + '     |   ' + str(cliente.fecha))
        else:
           print('No hay estadiscticas disponibles!')

consultas(cliente)

