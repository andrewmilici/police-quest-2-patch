;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9)
(include game.sh)
(use Main)
;(use Intrface)
;(use Motion)
(use Game)
;(use Actor)
(use System)

(public
	rm9 0
)

(local
	localPixelSave	
)


(instance rm9 of Room
	(properties
		picture 701
		style DISSOLVE
	)
	
	(method (init)

		(super init:)
		(self setScript: RoomScript)
	


	)
)

(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(= state newState)
		(switch state
			(0 ; Handle state changes
				(= seconds 3)
			)
			(1
				
				;(= localPixelSave (Display 9 0 dsCOORD 90 80 dsCOLOUR clRED dsBACKGROUND clTRANSPARENT dsSAVEPIXELS))
				
				(= localPixelSave
					(Display 9 0
						101 teJustCenter	;101 should be p_mode
						p_at 90 80
						p_color vRED
						p_save
					)
				)
				
				(= seconds 3)	
			)
			(2
				
				(Display 9 1 p_restore localPixelSave)
				(= seconds 3)	
			)
			(3
				(curRoom newRoom: 35)
			)
		)
	)	
	
)


