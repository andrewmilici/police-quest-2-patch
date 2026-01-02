;;; Sierra Script 1.0 - (do not remove this comment)
(script# 37)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)
(use Avoider)
(use User)
(use Sound)
(public
	rm37 0
)

(local
	newLoop
	newCel
	newProp
	newKeithProp
	waiter
	[order 100]
	[keithOrder 100]
	keith
	satDown
	foodServed
	orderTaken
	foodEaten
	paidForMeal
	shootOutHappening
	didARunner
)

(procedure (LocalPrint)
	(Print &rest #at -1 125)
)

(procedure (SonnySays)
	(Print &rest
		#title {You Say:}
		#at 260 25 )	
)

(procedure (KeithSays)

	(Print &rest
		#title {Keith says:}
		#at 3 25 )
)


(instance dinerList of List

	(properties)
)

(instance mealList of List

	(properties)
)


(procedure (fillMealList &tmp i)
	(for ((= i 5)) (< i 23) ((++ i))
		(mealList add: i)
	)
)

(procedure (fillDinerList &tmp i)
	(for ((= i 1)) (< i 10) ((++ i))
		(dinerList add: i)
	)
)

(procedure (getMealListCount &tmp retval node)
		(= retval 0)
		(= node (mealList first:))
		(while node
			(++ retval)
			(= node (mealList next: node))
		)	
		(return retval)
)

(procedure (getRandomMealItem &tmp temp0 max node)
	(= max (getMealListCount))
	(= temp0 (Random 1 max))
	(= node (mealList at: temp0))
	(mealList delete: node)	
	(return node)
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

(instance dinnerMusic of Sound
	(properties
		number 8
		priority 1
	)
)


(instance rm37 of Room
	(properties
		picture 30
		style DISSOLVE
	)
	
	(method (init &tmp temp0)
;;;		(= temp0  (Print {Is Keith Here?}
;;;			#button	{ Yes } 1
;;;			#button { No } 0
;;;		))
;;;		
;;;		(if (== temp0 1)
;;;			(Bset fKeithFollows)
;;;		)
		
		
		(super init:)
		(fillDinerList)
		(fillMealList)
		
		(Load VIEW 1)
		(Load VIEW 0)
		(Load VIEW 266)
		(Load VIEW 28)
		((= newProp (Prop new:)) init:)
		((= newKeithProp (Prop new:)) init:)
		(ego
			view: 1
			setStep: 3 2
			init:
			posn: 107 165
			setMotion: MoveTo 107 130
		)
		(= satDown 0)
			
	(Load VIEW 266)
;;;		((= waiter (Actor new:))
;;;			view: 28
;;;			posn: 137 104
;;;			init:
;;;			setCycle: Walk
;;;			setStep: 3 2
;;;		;	setScript: waiterScript
;;;		)
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
			posn: 175 111
			loop: 0
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
		
		((= waiter (Actor new:))
			view: 28
			posn: 137 104
			init:
			setCycle: Walk
			setStep: 3 2
			;setScript: waiterScript
		)		
		
		(if (Btst fKeithFollows)
			((= keith (Actor new:))
				view: 20
				init:
				setCycle: Walk
				setAvoider: (Avoider new:)
				setMotion: Follow ego 25
			)
			(keith posn: (ego x?) (+ (ego y?) 10))
		)		
		
		
		(dinnerMusic play:)				
		(HandsOn)
		(self setScript: RoomScript)


	)
	(method (doit)
		(if (> (ego y?) 165)


			;;THIS IS BAD CODE AND I NEED TO RE-WRITE IT - IM TIRED
			(if (and (== foodEaten 1) (== paidForMeal 0))
				(= shootOutHappening 1)
				(self setScript: waiterScript)
				(waiterScript changeState: 27)
			else 
				(if (== shootOutHappening 0)
					(curRoom newRoom: 29)	
				)
			)
			
		)
		(super doit:) 
		;((> (ego y?) 165) (= dateState 5) (curRoom newRoom: 105))
	)
		
	(method (dispose)

		(waiterScript dispose:)
		;(keithScript dispose:)	
		(super dispose:)

	)
)


(instance sonnyScript of Script
    (properties)
    
    (method (changeState newState)
        (= state newState)
        (switch state
            (0
				(HandsOff)
				(newProp startUpd:)
				(newProp
					view: 266
					posn: 209 111
					loop: 9
					cel: 0
					setCycle: Forward)
				(= cycles 10)
            )
            (1
            	(= cycles 100)
            	
			)
            (2
                (LocalPrint 37 (Random 32 41))
                (= cycles 100)
                ;(HandsOn)
                ;(self dispose:)
            )
            (3
            	(if (== (Random 1 2) 1)
            		(ShakeScreen 5)
            		(Print 37 44)
            		(Print 37 45 #at -1 150)
				)
				(= cycles 50)
			)
			(4
				(= foodEaten 1)
				(newProp
					loop: 6
					cel: 0
					stopUpd:)
				(HandsOn)
                ;(self dispose:)
			)
			(5
				(ego loop: 1 cel: 3 posn: 214 112)
				(newProp posn: -100 0 stopUpd:)
				(User canControl: 1)
				(self cue:)	
				
			)
			
        )
    )
)

(instance waiterScript of Script
	(properties)
	
	
	(method (changeState newState &tmp [str 150] temp0)
		(= state newState)
		(switch state
			;;; START - WAITER TELLS YOU TO SIT DOWN
			(0 
				(User canControl: 0)
				(waiter setMotion: MoveTo 103 104 self startUpd:)
			)
			(1
				(waiter setMotion: MoveTo 103 115 self)
			)
			(2
				(waiter stopUpd: (Print 30 105 #at 68 150))
				(self cue:)
				(User canControl: 1)
			)
			(3
				(waiter setMotion: MoveTo 103 104 self startUpd:)
			)
			(4
				(waiter setMotion: MoveTo 127 104 self)
			)
			(5 (waiter stopUpd:))
			;;; WAITER COMES TO TAKE YOUR ORDER
			(6
				(User canControl: 0 canInput: 0)
				(waiter
					ignoreActors: 1
					illegalBits: 0
					setCycle: Walk
					setLoop: -1
					setStep: 3 2
					setMotion: MoveTo 103 104 self
					startUpd:
				)
			)
			(7
				(waiter setMotion: MoveTo 103 113 self)
			)
			(8
				(waiter setPri: 8 setMotion: MoveTo 194 113 self)
			)
			(9
				(waiter setMotion: MoveTo 194 112 self)
			)			
			(10
				(waiter stopUpd:)
				(cond
					((and (== foodEaten 1) (== paidForMeal 0)) ; ORGANISE PAYMENT
						 (self changeState: 36)
					)
					((== orderTaken 0) ;TAKE ORDER
						(User canInput: 1)
						(Format @str 37 4 
							37 (getRandomMealItem) 
							37 (getRandomMealItem) 
							37 (getRandomMealItem))

						(Print @str #at -1 120)
						(Print 30 107 #at -1 150)
						(Format @str 37 23)
						(GetInput @order 50 {What do you want?})
						(= cycles 10)
					)
					(else 
						(cond
							((and (== orderTaken 1) (== foodServed 0))
								(LocalPrint 37 59)
								(self changeState: 16)	
							)	
							((and (== foodEaten 1) (== paidForMeal 1))
								(LocalPrint 37 60)
								(self changeState: 16)	
							)
						)
						
						
					)
				)	
;;;				(cond
;;;					((and (== foodEaten 1) (== paidForMeal 0)) (self changeState: 36))
;;;					((== orderTaken 0) (Print {take order}))
;;;					()
;;;					
;;;				)
;;;				

			)
			(11
				(Format @str 37 24 @order)
				(Print @str #at -1 120)
				(Format @str 37 23)
				(= cycles 20)
			)
			(12
				(if (not (cast contains: keith))
					(self changeState: 15)	
				
				else
					(Print 37 25 #at -1 120)
					(= cycles 20)
				)
			)			
			(13
				(Format @keithOrder 37 (getRandomMealItem))
				(Format @str 37 26 @keithOrder)
				(Print @str #at -1 120)
				(= cycles 20)
			)
			(14
				;37 27
				(Format @str 37 27 @keithOrder)
				(Print @str #at -1 120)
				(= cycles 20)
			)
			(15
				(LocalPrint 30 115)
				(waiter setMotion: MoveTo 103 113 self startUpd:)
				(User canInput: 0)
				(= orderTaken 1)
			)
			(16
				(waiter setMotion: MoveTo 103 104 self)
			)
			(17
				(waiter setPri: 6 setMotion: MoveTo 125 102 self)
			)
			(18
				(waiter stopUpd:)
				(User canInput: 1)
				(= seconds 30)
			)
			;;; WAITER COMES OUT WITH FOOD ON TRAY
			(19
				(if (== foodServed 0)
					(LocalPrint 30 116)
					(HandsOff)
					(waiter startUpd:)
					(waiter
						view: 44
						setLoop: 5
						setMotion: MoveTo 103 104 self)
			
				)
			)
			(20
				(waiter
					setLoop: 6
					setPri: 8
					setMotion: MoveTo 103 120 self)
			)
			(21
				(waiter 
					setLoop: 4 
					setMotion: MoveTo 190 113 self)
			)
			(22
				(waiter 
					setLoop: 7 
					setMotion: MoveTo 190 112 self)
			)
			(23
				(waiter stopUpd:)
				(LocalPrint 30 117 25 3)
				(waiter
					view: 44
					loop: 1
					cel: 0
					posn: 200 113
					setMotion: MoveTo 100 113 self
					startUpd:
				)
				(= foodServed 1)
				(HandsOn)
			)
			(24
				(waiter
					setLoop: 3
					setPri: 6
					setMotion: MoveTo 100 104 self
				)
			)
			(25
				(waiter setLoop: 0 setMotion: MoveTo 125 102 self)
			)
			(26 
				(waiter stopUpd:)
				;(self 
			)
			(27 ;Waiter shoots at sonny for not paying
				;(Print {shoot out})
				(waiter startUpd:)
				(waiter 
					view: 28
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 103 104 self 
					startUpd:)
					
			
					
				(HandsOff)
				(ego
					setMotion: MoveTo 109 135 self)
			)
			(28
				(if (cast contains: keith)
					(keith 
						setCycle: Walk
						setMotion: MoveTo 162 116)
							
				else
					(= cycles 20)	
				)	
				
			)
			(29
				(waiter setMotion: MoveTo 103 115 self)	
			)
			(30
				(waiter view: 41 setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(31
				(waiter view: 41 setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(32
				(waiter view: 41 setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)			
			(33
				(waiter view: 41 setLoop: 1 setCel: 0 stopUpd:)
				(ego view: 97 loop: 1 setCycle: EndLoop self)
			)
			(34
				(= cycles 5)	
			)
			(35
				(if (== didARunner 1)
					(EgoDead 37 53)	
				else
					(EgoDead 37 46)	
				)
				
			); PAY FOR DINNER
			(36
				;(= temp0 5)
				(= temp0 (Random 20 110))
				(Format @str 37 47 temp0)
				(LocalPrint @str)
				
				(if (<= temp0 dollars)
					(= dollars (- dollars temp0))
					(LocalPrint 37 49)
					(= temp0 (Print 37 50
						#button	{ Tip 20% } 20
						#button { Tip 10% } 10
						#button { Tip 5% } 5
						#button { No tip } 0
					))
					(Format @str 37 54 temp0)
					(LocalPrint @str)
					(switch temp0
						(20
							(LocalPrint 37 56)
						)	
						(10
							(LocalPrint 37 57)
						)
						(5
							(LocalPrint 37 58)	
						)
						(0
							(LocalPrint 37 55)
						)
						
					)
					(= paidForMeal 1)
					(self changeState: 16)	
				
					
				else
					(Format @str 37 48 dollars)
					(LocalPrint @str)
					(if (cast contains: keith) ; keith pays
						(LocalPrint 37 51)
						(LocalPrint 37 52)
						(= paidForMeal 1)
						(self changeState: 16)	
				
					else ; sonny does a runner
					(= didARunner 1)
						
						(ego loop: 1 cel: 3 posn: 214 112)
						(newProp posn: -100 0 stopUpd:)
						(ego
							startUpd:
							setCycle: Walk
							setMotion: MoveTo 154 122 self)
						;(User canControl: 1)
						;(self cue:)	
					)
				
				)
				
			)
			(37
				(self changeState: 30)	
			)

			
		)
	)		
)


(instance RoomScript of Script
	(properties)
	
	(method (changeState newState)
		(= state newState)
			(switch state
				(0
					
				)
				(1
					(keith setCycle: Walk
						   setMotion: MoveTo 175 111 self  )
				)
				(2
					(keith posn: -100 0 stopUpd:)	
					(newKeithProp
						view: 266
						posn: 178 111
						loop: 14
						cel: 0
						ignoreActors:
						init:
						stopUpd:)	
				)
			)
	)
	
	(method (handleEvent event &tmp temp0)
		(switch (event type?)
			(saidEvent

				(cond 
					
					((Said 'take/money')
						(Printf {current ego %u,%u} (ego x?) (ego y?))
						(if (ego inRect: 99 125 119 136)
							(LocalPrint 37 1)
							(LocalPrint 37 2)
							(Print 37 3 #at -1 125)
							(+= dollars 37)
						else
							(NotClose)
						)
					)
					;
					;
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*,chamber,cafe,arnie]') (LocalPrint 30 3))
							((Said '<below/table') (LocalPrint 30 4))
							((Said '/table') (LocalPrint 30 5))
							((Said '/wall') (Print 30 6 #at -1 125))
							((or (Said '<up') (Said '/ceiling')) (LocalPrint 30 7))
							((or (Said '<down') (Said '/floor')) (LocalPrint 30 8))
							((Said '/partition') (LocalPrint 30 9))
							((Said '/counter') (LocalPrint 30 10))
							((Said '/phone') (Print 30 11))
							((Said '/desk') 
								(LocalPrint 30 12)
								(LocalPrint 37 0)
							)
							((Said '/chow')
							;	(if local31
							;		(LocalPrint 30 13)
							;	else
									(LocalPrint 30 14)
							;	)
							)
							((Said '/dude')
								(if (<= (waiter distanceTo: ego) 20)
									(LocalPrint 30 15)
								else
									(LocalPrint 30 16)
								)
							)
							((Said '/crowd,couple,customer') (LocalPrint 30 16))
							((Said '/waiter')
								(if
								(and (< (waiter y?) 105) (> (waiter x?) 105))
									(LocalPrint 30 17)
								else
									(LocalPrint 30 15)
								)
							)
							((Said '/computer,terminal,register') (LocalPrint 30 21))
							((Said '/plant') (LocalPrint 30 22))
							((Said '/painting,print') (LocalPrint 30 23))
							((Said '/grass') (LocalPrint 30 24))
							((Said '/pane') (LocalPrint 30 25))
						)
					)					
					
					((Said 'eat[/chow,chow,meatloaf,loaf,rib,lobster]')
						(cond
							((== satDown 0)
								(Print 37 29)
								(Print 37 30 #at -1 155)
							)
							((== foodServed 0)
								(if (cast contains: keith)
									(Print 37 42)
									(Print 37 43)
								else
									(Print 37 31)
								)
							)
							(
								(self setScript: sonnyScript)
								(sonnyScript start:)	
								
							)
						)	
						
					)
					((Said 'sat[<down]')
						(if (ego inRect: 203 105 233 120)
							(User canControl: 0)
							(ego posn: -100 0 stopUpd:)
							(newProp
								view: 266
								posn: 209 111
								loop: 6
								cel: 0
								ignoreActors:
								init:
								stopUpd:)	
								
							(= satDown 1)	
							(if (cast contains: keith)
								
								;(keithScript changeState: 3)
								(self changeState: 1)
							)					
						
						)
					)
					(
						(or
							(Said 'pay/waiter,bill,chow,chow')
							(Said 'get,get/bill,check')
						)
						(waiterScript changeState: 6)

					)					
					((Said 'stand,(get<up)')
						(sonnyScript changeState: 5)
						
					)
					((or (Said 'get,get/chow') (Said '[call,get]/waiter'))
						(waiter setScript: waiterScript)

						(if (== satDown 1)
							(waiterScript changeState: 6) 
						else
							(waiterScript changeState: 0) 
						)
						

					    
					)
					
					((Said 'chat>')
						(cond 
							((Said '/dude,crowd,customer,cafe,customer')
								(cond 
									((> (ego x?) 105)
										(= temp0 (Random 61 69))
										(LocalPrint 37 temp0)
										
										(if (== temp0 69)
											(LocalPrint 37 70)	
										)
									)
									(else (LocalPrint 30 47))	
								)
							)
							((Said '/keith')
								(if (cast contains: keith)
									(= temp0 (+ 71 (* (Random 0 4) 2)))
									(SonnySays 37 temp0)
									(KeithSays 37 (+ temp0 1))
								else
									(LocalPrint 37 81)
								)
							
							)
							(else (LocalPrint 30 57))
						)
						(event claimed: 1)
					)					
					
					
				)
			)
		)
	)	
	
	
)


