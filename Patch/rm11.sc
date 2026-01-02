;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use System)

(public
	RemoveClothesScript 0
)

;;; Putting this in a seperate script was the only way i could get it to work. 
(instance RemoveClothesScript of Script
	(properties)

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 990
					loop: 0
					cel: 0
					;cycleSpeed: 1
					;setCycle: End self
				)

				(= cycles 5)
			)
			(1
				(Print 0 44)
				(EgoDead 0 45)	
				
			)
		)
	)
)

