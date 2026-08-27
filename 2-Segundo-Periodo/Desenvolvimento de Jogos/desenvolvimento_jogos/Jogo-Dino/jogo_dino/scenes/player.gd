extends CharacterBody2D

# Define a velocidade de caminhada (em pixels/segundo).
const SPEED = 150.0
const JUMP_VELOCITY = -400.0 # É boa prática usar .0 para floats
# Define a gravidade (em pixels/segundo²).
# CORREÇÃO: Vamos pegar a gravidade direto das configurações do projeto.
# Assim, se você mudar lá, muda aqui automaticamente.
const GRAVITY = 980

@onready var anim = $animation as AnimatedSprite2D
# CORREÇÃO: A variável 'jumpPlayer' não é necessária.

func _physics_process(delta: float) -> void:
	
	# Começamos com a velocidade do frame anterior.
	var vel = velocity 

	# 1. Aplicar Gravidade (APENAS UMA VEZ)
	# Só aplicamos gravidade se o personagem estiver no ar.
	if not is_on_floor():
		vel.y += GRAVITY * delta
		
	# 2. Processar Pulo
	# Verificamos o pulo DEPOIS da gravidade, para que o pulo tenha prioridade.
	if Input.is_action_just_pressed("ui_accept") and is_on_floor():
		vel.y = JUMP_VELOCITY # Define a velocidade vertical para o pulo

	# 3. Processar Movimento Lateral
	var direction := Input.get_axis("ui_left", "ui_right")

	if direction:
		anim.scale.x = direction # Vira o sprite
		vel.x = direction * SPEED
	else:
		# Se nenhuma tecla for pressionada, pare (aplique atrito).
		vel.x = move_toward(vel.x, 0, SPEED)

	# 4. Lógica de Animação (Simplificada)
	# É mais fácil verificar o ar primeiro.
	if not is_on_floor():
		anim.play("jumping") # Se está no ar, toque "jumping"
	else:
		# Se está no chão...
		if direction == 0:
			anim.play("idle") # Se parado, toque "idle"
		else:
			anim.play("running") # Se movendo, toque "running"
	
	# 5. Aplicar o movimento final
	velocity = vel
	move_and_slide()
	
	# 6. Lógica de Morte (Movida para o final, após o movimento)
	if position.y > 400:
		death()

# A sua função de morte está perfeita!
func death():
	anim.play("hurt")
	await get_tree().create_timer(2.0).timeout
	get_tree().reload_current_scene()
