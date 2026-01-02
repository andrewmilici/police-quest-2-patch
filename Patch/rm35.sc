;;; Sierra Script 1.0 - (do not remove this comment)
(script# 35)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)
(use Avoider)
(use Sound)
(public
	rm35 0
)

(local
	sonnyEat
	dateEat
	captainHall	
	newLoop
	newCel
)

(instance dinerList of List

	(properties)
)


(instance dinnerMusic of Sound
	(properties
		number 8
		priority 1
	)
)
(procedure (fillDinerList &tmp i)
	(for ((= i 1)) (< i 10) ((++ i))
		(dinerList add: i)
	)
)

;;;(procedure (SonnySays
(procedure (SonnySays)
	(Print &rest
		#title {You Say:}
		#time 3
		#at 260 25 
		#dispose)	
)

(procedure (DateSays)

	(Print &rest
		#title 
			(switch gCurrentDate
				(0
					{Crusher Says:}	
				)	
				(1
					{Shelley Says:}
				)
			)
		#time 4
		#at 3 25 
		#dispose)	
)

(procedure (getDinerListCount &tmp retval node)
		(= retval 0)
		(= node (dinerList first:))
		(while node
			(++ retval)
			(= node (dinerList next: node))
		)	
		(return retval)
)

(procedure (randomLoop param1 &tmp idx max node)
    (= max (getDinerListCount))
    (if (<= max 0)
        (return) ; or (fillDinerList) then recalc max
    )

    (= idx (Random 0 (- max 1)))
    (= node (dinerList at: idx))
    (dinerList delete: node)

    ; optional: reset so a missed switch can't reuse old values
    (= newLoop 0)
    (= newCel 0)

	(switch node
		(1
			(if (== param1 0)
				(= newLoop 3)
				(= newCel 2)
			else
				(= newLoop 3)
				(= newCel 1)							
			)
		)
		(2
			(if (== param1 0)
				(= newLoop 12)
				(= newCel 0)
			else
				(= newLoop 3)
				(= newCel 4)							
			)
		)
		(3
			(if (== param1 0)
				(= newLoop 12)
				(= newCel 1)
			else
				(= newLoop 11)
				(= newCel 0)						
			)
		)
		(4
			(if (== param1 0)
				(= newLoop 12)
				(= newCel 2)
			else
				(= newLoop 11)
				(= newCel 1)				
			)
		)
		(5
			(if (== param1 0)
				(= newLoop 12)
				(= newCel 3)
			else
				(= newLoop 11)
				(= newCel 2)							
			)
		)
		(6
			(if (== param1 0)
				(= newLoop 12)
				(= newCel 4)
			else
				(= newLoop 11)
				(= newCel 3)							
			)
		)
		(7
			(if (== param1 0)
				(= newLoop 12)
				(= newCel 5)
			else
				(= newLoop 11)
				(= newCel 4)							
			)
		)
		(8
			(if (== param1 0)
				(= newLoop 12)
				(= newCel 6)
			else
				(= newLoop 11)
				(= newCel 5)							
			)
		)														
		(9
			(if (== param1 0)
				(= newLoop 12)
				(= newCel 6)
			else
				(= newLoop 11)
				(= newCel 6)							
			)
		)		
			
	)
	

)

(instance rm35 of Room
	(properties
		picture 30
		style DISSOLVE
	)
	
	(method (init)

		(Load VIEW 266)
		(fillDinerList)
	;PATRONS
		(randomLoop 1)
		((View new:)
			view: 266
			posn: 224 133
			loop: newLoop
			cel: newCel ;right side
			setPri: 9
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 206 130
			loop: 3 ;back side
			cel: 3
			setPri: 9
			init:
			stopUpd:
			addToPic:
		)
		(randomLoop 0)
		((View new:)
			view: 266
			posn: 123 111
			loop: newLoop
			cel: newCel ;left side
			init:
			stopUpd:
			addToPic:
		)
		(randomLoop 1)
		((View new:)
			view: 266
			posn: 149 112
			loop: newLoop
			cel: newCel ;right side
			init:
			stopUpd:
			addToPic:
		)		
		((= sonnyEat (Prop new:)) 
			init:
			view: 266
			posn: 209 111
			loop: 9
			cel: 
			setCycle: Forward)
			
		


		;((= captainHall (Act new:)) 
			;init:
			;view: 19
			;posn: 112 151)	
			
		((= captainHall (Actor new:))
			view: 19
			init:
			illegalBits: 0

		)	
		
((View new:)
			view: 266
			posn: 268 105
			loop: 4
			cel: 2
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 59 100
			loop: 4
			cel: 2
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 194 91
			loop: 4
			cel: 2
			setPri: 7
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 134 91
			loop: 4
			cel: 2
			setPri: 7
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 200 109
			loop: 4
			cel: 2
			setPri: 9
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 187 130
			loop: 2
			cel: 0
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 48 111
			loop: 4
			cel: 1
			setPri: 9
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 215 110
			loop: 1
			cel: 0
			ignoreActors:
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 102 98
			loop: 3
			cel: 0
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 56 98
			loop: 4
			cel: 0
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 266
			posn: 271 86
			loop: 4
			cel: 4
			init:
			stopUpd:
			addToPic:
		)				
		(dinnerMusic play:)
		(super init:)
		
		(switch gCurrentDate
			(0 		;Sicko
				((= dateEat (Prop new:)) 
					init:
					view: 266
					posn: 178 111
					loop: 10
					cel: 0
					cycleSpeed: 3
					setCycle: Forward)	
						
				(self setScript: SickoScript)
			)	
			(1  	;Jogger
				((= dateEat (Prop new:)) 
					init:
					view: 266
					posn: 178 111
					loop: 13
					cel: 0
					cycleSpeed: 3
					setCycle: Forward)	
						
				(self setScript: JoggerScript)				
				
			)
			
		)
		
		


	)
	
		
	(method (dispose)
	
		(SickoScript dispose:)
		(captainHallScript dispose:)
	
		(super dispose:)

	)
)

(instance captainHallScript of Script
	(properties)

	(method (changeState newState)
		;(Printf {captainHallScript %u} newState)
		(= state newState)
		(switch state
			(0 ; Handle state changes
				(captainHall
					posn: 93 157
					setPri: 10
					;cel: 3
					;setCycle: 0
					setCycle: Walk
					setMotion: MoveTo 114 116 self
					setAvoider: (Avoider new:)
					ignoreActors: 1
				)
			)
			(1
				(captainHall
					setMotion: MoveTo 176 113 self)	
				
			)
			(2
				(Print 35 5 
					#title {Hall says:}
					#at -1 25
					#time 2)
					
				(= seconds 2)
			)
			(3

				(= cycles 5)
				
			)
			(4		
			
				(curRoom newRoom: 36)

			)
		)
	)
)

(instance JoggerScript of Script
	(properties)
	
	(method (changeState newState)
		(= state newState)
		(switch state
			(0 ; Handle state changes
				(= seconds 5)
			)
			(1
				(DateSays 35 6)	
				(= seconds 4)
			)
			(2
				(DateSays 35 7)	
				(= seconds 4)
			)
			(3
				(Print 35 8)	
				(= cycles 10)
			)
			(4
				(DateSays 35 9)	
				(= seconds 4)
			)	
			(5
				(sonnyEat setCycle: 0)	
				(= seconds 3)	
			)	
			(6
				(sonnyEat setCycle: Forward)
				(DateSays 35 10)
				(= seconds 4)	
			)
			(7
				(= gCaptainHallReamScript 3)
				(captainHall setScript: captainHallScript)	
				
			)						
		)
	)	
	
)

(instance SickoScript of Script
	(properties)
	
	(method (changeState newState)
		(= state newState)
		(switch state
			(0 ; Handle state changes
				(= seconds 5)
			)
			(1
				(DateSays 35 0)
				(= seconds 4)
			)
			(2
				(SonnySays 35 2)
				(= seconds 2)
			)
			
			(3
				(DateSays 35 3)
				(= seconds 4)
			)
			(4
				(sonnyEat setCycle: 0)	
				(= seconds 2)
			)
			(5
				(DateSays 35 1) 
				(= seconds 5)
			)
			(6
				(sonnyEat setCycle: Forward)	
				(SonnySays 35 4)			
				(= seconds 5)			
			)
			(7
				(= gCaptainHallReamScript 1)
				(captainHall setScript: captainHallScript)
			)
		)
	)	
	
)


