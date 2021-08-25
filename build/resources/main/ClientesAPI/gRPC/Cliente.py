import EnlaceRn_pb2
import EnlaceRn_pb2_grpc
import grpc

user = 'anonimo'
enlacesAnonimo = []    
channel = grpc.insecure_channel('localhost:7001')
stub = EnlaceRn_pb2_grpc.EnlaceRnStub(channel)

def consultas(stub):
    menu1 = '\nCliente de Acortador gRPC\n 1 - Iniciar Sesion\n 2- Continuar como usuario anonimo(Los usuarios anonimos no podran visualizar las url creadas una vez terminado el programa) \n 3- Salir'
    res = 0

    while(res != 3):
        print(menu1)
        res = int(input('\nIngrese la opcion deseada: '))

        if res > 0 and res < 4:
            if res == 1:
                autentificar(stub)
            if res == 2:
                masOpciones(stub)

def autentificar(stub):
    aux = True
    global user
    while(aux):
        print('\n\n==== Log-in ====\n')

        us = input('Ingrese su nombre de usuario: ')
        pas = input('Ingrese su contraseña: ')
        res = stub.autentificacion(EnlaceRn_pb2.usuarioRequest(usuario = us,password = pas))

        if(res.ok):
            user = us
            masOpciones(stub)
            return
        else:
            res = int(input('\nUsuario o Contraseña incorrectos!\n 1 - Reintentar\n 2 - Ingresar como anonimo\nSelecione una opcion: '))

            if(res < 1 or res > 2):
                print('\nOpcion Invalida! Intente denuevo\n')
            elif(res == 2):
                masOpciones(stub)
                return

def masOpciones(stub):
    res = 0
    global user 
    global enlacesAnonimo
    while(res != 4):
        res = int(input("\nOpciones:\n 1 - Acortar enlace\n 2 - Listar Enlaces\n 3 - Visualizar un Enlace por ID\n 4 - Cerrar Sesion\n 5 - Salir\nSelecione una opcion: "))

        if(res > 0 and res < 6):
            if(res == 1):
                acortar(stub)
            if(res == 2):
                listar(stub)
            if(res == 3):
                mostrar(stub)
            if(res == 4):
                user = 'anonimo'
                enlacesAnonimo = []

            if(res == 5):
                exit()
        else:
            print('\n\nOpcion Invalida! Intente denuevo')

def acortar(stub):
    url = input('\nIngrese la URL que desea acortar: ')
    res = stub.registrarEnlace(EnlaceRn_pb2.EnlaceRequest(enlace = url, usuario = user))
    
    if(res == None):
        print('\nError! Por favor revise la URL')
    else:
        print('\n\n===================Resultado===================')
        print("ID: " + str(res.enlaceId))
        print('URL Original: '+  res.url)
        print('URL Acortada: ' + res.acortado)
        print('Fecha de creacion: '+ res.fecha)
        print('Foto en Base64: '+ res.foto[0:20]+'...')
        print('===================================================\n\n')

    if user == 'anonimo':
        enlacesAnonimo.append(res)

def listar(stub):
    if user == 'anonimo':
        res = enlacesAnonimo
    else:
        res = stub.getEnlaces(EnlaceRn_pb2.enlace(user = user)).elace

    print('===================Resultado===================')

    for enlace in res:
        print("ID: " + str(enlace.enlaceId))
        print('URL Original: '+  enlace.url)
        print('URL Acortada: ' + enlace.acortado)
        print('Fecha de creacion: '+ enlace.fecha)
        print('Cantidad de veces accedidas: '+ str(enlace.veces))
        print('Foto en Base64: '+ enlace.foto[0:20]+'...')
        print('===================================================')

def mostrar(stub):
    id = int(input('\nIngrese el ID del enlace que desea visualizar: '))
    if(id > 0):
        res = stub.getEnlace(EnlaceRn_pb2.clientesRequest(idEnlace = id))
        clientes = stub.getClientes(EnlaceRn_pb2.clientesRequest(idEnlace = id))
        print('\n\n===================Resultado===================\n')
        print("ID: " + str(res.enlaceId))
        print('URL Original: '+  res.url)
        print('URL Acortada: ' + res.acortado)
        print('Fecha de creacion: '+ res.fecha)
        print('Cantidad de veces accedidas: '+ str(res.veces))
        print('Foto en Base64: '+ res.foto[0:20]+'...')
        print('\n=======================Estadisticas=======================\n')

        if res.veces > 0:
            print('|     IP        |    Navegador   y   Sistema  |   Fecha')
            for cliente in clientes.clientes:
                print('  '+cliente.ip + '  |     ' + cliente.navegador+ '   -   '+cliente.sistema + '     |   ' + cliente.fecha)
        else:
            print('No hay estadiscticas disponibles!')
consultas(stub)

