extends CharacterBody2D

const SPEED = 300.0

# CORREÇÃO: Apontamos para o seu nó chamado "animacao"
@onready var animated_sprite = $animacao

func _physics_process(delta: float) -> void:
	var direction = Input.get_axis("ui_left", "ui_right")
	
	# Lógica de Animação e Direção do Sprite
	if direction != 0:
		# Se estiver se movendo...
		velocity.x = direction * SPEED
		animated_sprite.play("walk") # Toca a animação de andar
		
		# Vira o sprite para a direção correta
		if direction > 0:
			animated_sprite.flip_h = false # Não vira (olhando para a direita)
		else:
			animated_sprite.flip_h = true  # Vira horizontalmente (olhando para a esquerda)
	else:
		# Se estiver parado...
		velocity.x = move_toward(velocity.x, 0, SPEED)
		animated_sprite.play("idle") # Toca a animação de parado

	move_and_slide()
