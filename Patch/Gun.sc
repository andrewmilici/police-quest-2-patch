;;; Sierra Script 1.0 - (do not remove this comment)
(script# regGun)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use System)

(public
	Gun 0
)

(local
	savIllegalBits
	keithDirection
)
(instance GunShot of Sound
	(properties
		priority 12
	)
)

(instance Gun of Locale
	(method (init)
		(Load SOUND 41)
		(Load SOUND 43)
	)
	
	(method (handleEvent event &tmp [temp0 54])
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(switch (event message?)
					(`#6
						(event claimed: TRUE)
						(cond 
							(isHandsOff 0)
							((not (ego has: iHandGun))
								(DontHaveGun)
							)
							(
								(or
									(not (ego has: iAmmoClips))
									(and
										(== [numAmmoClips 1] [numAmmoClips 2])
										(== [numAmmoClips 2] 0)
									)
								)
								(Print 150 0)
							)
							([numAmmoClips bulletsInGun]
								(Print 150 1)
							)
							(else
								(Print 150 2 #time 4)
								(if (== bulletsInGun 1)
									(= bulletsInGun 2)
								else
									(= bulletsInGun 1)
								)
							)
						)
					)
					(`#8
						(event claimed: TRUE)
						(if (not isHandsOff)
							(if gunDrawn
								(ego setScript: holsterGun)
							else
								(ego setScript: drawGun)
							)
						)
					)
					(`#a	;F10
						(event claimed: TRUE)
						(if (not isHandsOff)
							(ego setScript: fireGun)
						)
					)
				)
			)
		)
	)
)

(instance drawGun of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not (ego has: iHandGun))
						(client setScript: 0)
						(DontHaveGun)
					)
					(global214 0)
					(isHandsOff 0)
					(else
						(HandsOff 1)
						(= global214 1)
						(ego
							view:
							(switch (ego view?)
								(1 5)
								(296 305)
								(0 4)
							)
							setCel: 0
							setCycle: EndLoop self
						)
					)
				)
			)
			(1
				(= global214 0)
				(ego
					view:
					(switch (ego view?)
						(5 7)
						(305 306)
						(4 6)
					)
					setCel: 0
					init:
				)
				(HandsOn 1)
				(= gunDrawn TRUE)
				(client setScript: 0)
				(if gunNotNeeded
					(Print 800 (Random 35 38))
				)
			)
		)
	)
)

(instance fireGun of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not (ego has: iHandGun))
						(client setScript: 0)
						(DontHaveGun)
					)
					((not gunDrawn)
						(Print 150 3)
						(client setScript: 0)
					)
					(global213 0)
					(isHandsOff 0)
					((== [numAmmoClips bulletsInGun] 0)
						(GunShot number: 43 priority: 10 play:)
						(Print 150 4 #time 2)
						(client setScript: 0)
					)
					(else
						(HandsOff 1)
						(GunShot number: 41 priority: 10 play:)
						(= global213 1)
						(-- [numAmmoClips bulletsInGun])
						(= savIllegalBits (ego illegalBits?))
						(ego ignoreActors: ignoreControl: -1)
						(cond 
							((or (== (ego view?) 6) (== (ego view?) 4))
								(ego
									view: 4
									loop: (+ 4 (mod (ego loop?) 4))
									setCel: 0
									setCycle: EndLoop self
								)
							)
							((or (== (ego view?) 7) (== (ego view?) 5))
								(ego
									view: 5
									loop: (+ 4 (mod (ego loop?) 4))
									setCel: 0
									setMotion: 0
									setCycle: EndLoop self
								)
							)
						)
					)
				)
			)
			(1
				(ego ignoreActors: 0 illegalBits: savIllegalBits)
				(= global213 0)
				(= global205
					(switch (mod (ego loop?) 4)
						(0 2)
						(1 4)
						(2 3)
						(3 1)
					)
				)
				(ego
					view: (if (== (ego view?) 4) 6 else 7)
					loop: (- (ego loop?) 4)
					setStep: 3 2
					setCel: 0
					setCycle: Walk
				)
				(client setScript: 0)
				(switch gunFireState
					(0 0)
					(3
						(if (cast contains: keith)
							(curRoom setScript: keithShootout)
						else
							(Print 800 (Random 45 48))
							(curRoom newRoom: 92)
						)
					)
					(else
						(if (cast contains: keith)
							(curRoom setScript: keithShootout)
						else
							(Print 800 (Random 40 43))
						)
					)
				)
				(HandsOn 1)
			)
		)
	)
)

(instance keithShootout of Script
	(properties)
	
	(method (init &tmp temp0)
		
		;(super init: param1)
		;(person loop: 7 cel: 0 posn: 63 80 stopUpd:)
		;(personMouth loop: 7 cel: 0 posn: 74 69 setCycle: EndLoop)
		;(RedrawCast)
		(self changeState: 1)
	)
	
	(method (changeState newState &tmp evt obj [temp2 2] node [str 40])
        (= state newState)
        (switch state
            (1
            	(= cycles 3)
            )
            (2
            	
            	(HandsOff)
            	(if 
            		(<= (ego x:) (keith x:))
            		(= keithDirection 1)
            		;(keith view: 53 loop: 1)
            	else
            		(= keithDirection 0)
            		;(keith view: 53 loop: 0)
				)
         
				(keith view: 53 loop: keithDirection)
				(= cycles 5)
			)
			(3
				(keith view: 18 loop: keithDirection setCel: 0 setCycle: EndLoop self)
			)
			(4
				(keith view: 18 loop: keithDirection setCel: 0 setCycle: EndLoop self)
			)
			(5
				(keith view: 18 loop: keithDirection setCel: 0 setCycle: EndLoop self)
			)
			(6
				(keith view: 18 loop: keithDirection setCel: 0 setCycle: EndLoop self)
			)
			(7
				(keith view: 18 loop: keithDirection setCel: 0 setCycle: EndLoop self)
			)
			(8
				(keith view: 53)
				
				(if (== keithDirection 1)
					(ego view: 97 loop: 0 setCycle: EndLoop self)
				else
					(ego view: 97 loop: 1 setCycle: EndLoop self)
				)
				
				(HandsOn)
				(= cycles 20)				
			)
			(9
				(EgoDead 150 5)
			)
				
        )
	)
	
	(method (handleEvent event)
		
	)
)

(instance holsterGun of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff 1)
				(ego
					view:
						(switch (ego view?)
							(7 5)
							(5 5)
							(306 305)
							(305 305)
							(6 4)
							(4 4)
						)
					loop: (mod (ego loop?) 4)
					setCel: 255
					setCycle: BegLoop self
				)
			)
			(1
				(= gunDrawn FALSE)
				(ego
					view:
					(switch (ego view?)
						(5 1)
						(305 296)
						(4 0)
					)
					setCel: 0
					setCycle: Walk
				)
				(HandsOn 1)
			)
		)
	)
)
