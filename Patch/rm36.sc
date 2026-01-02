;;; Sierra Script 1.0 - (do not remove this comment)
(script# 36)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)
(use Avoider)

(public
	rm36 0
)

(procedure (CaptainSays)
	(Print &rest
		#font 4
		#at -1 145
		#width 280
	)
)

(instance rm36 of Room
	(properties
		picture 502
		style IRISIN
	)
	
	(method (init)

		(Load VIEW 500)
		(super init:)
		(HandsOff)
		(capFace
			view: 500
			loop: 0
			cel: 0
			ignoreActors:
			posn: 109 123
			setPri: 8
			init:
		)
		(capTorso
			view: 500
			loop: 0
			cel: 1
			ignoreActors:
			posn: 113 146
			setPri: 10
			init:
		)
		(capHand
			view: 500
			loop: 0
			cel: 2
			ignoreActors:
			posn: 163 157
			setPri: 0
			init:
		)
		(capLips
			view: 500
			loop: 1
			cel: 1
			ignoreActors:
			posn: 122 107
			setPri: 14
			setScript: speak
			init:
		)
		(sonny
			view: 500
			loop: 0
			cel: 3
			ignoreActors:
			posn: 213 147
			setPri: 14
			init:
		)
		(switch gCaptainHallReamScript
			(1
				(self setScript: dinnerDate)		
			)	
			(2
				(self setScript: stealMuff)		
			)
			(3
				(self setScript: joggerDate)	
				
			)
			
		)
		
	)
	
		
	(method (dispose)
	
		(super dispose:)

	)
)

(instance joggerDate of Script
	(method (changeState newState)
	(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				(CaptainSays 36 11 #dispose)
				(= seconds 10)
			)
			(2
				(cls)
				(CaptainSays 36 12 #dispose)
				(= seconds 10)
			)
			(3
				(cls)
				(CaptainSays 36 13 #dispose)
				(= seconds 10)
			)
			(4
				(cls)
				(CaptainSays 36 14 #dispose)
				(= seconds 10)
			)
			(5
				(cls)
				(EgoDead 36 15)
			)
		)		
		
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (event claimed?))
				(or
					(== (event type?) keyDown)
					(== (event type?) mouseDown)
				)
				modelessDialog
			)
			(event claimed: TRUE)
			(= cycles (= seconds 0))
			(self cue:)
		)
	)
	
)

(instance stealMuff of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				(CaptainSays 36 6 #dispose)
				(= seconds 10)
			)
			(2
				(cls)
				(CaptainSays 36 7 #dispose)
				(= seconds 10)
			)
			(3
				(cls)
				(CaptainSays 36 8 #dispose)
				(= seconds 10)
			)
			(4
				(cls)
				(CaptainSays 36 9 #dispose)
				(= seconds 10)
			)
			(5
				(cls)
				(EgoDead 36 10)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (event claimed?))
				(or
					(== (event type?) keyDown)
					(== (event type?) mouseDown)
				)
				modelessDialog
			)
			(event claimed: TRUE)
			(= cycles (= seconds 0))
			(self cue:)
		)
	)
)

(instance dinnerDate of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				(CaptainSays 36 0 #dispose)
				(= seconds 10)
			)
			(2
				(cls)
				(CaptainSays 36 1 #dispose)
				(= seconds 10)
			)
			(3
				(CaptainSays 36 2 #dispose)
				(= seconds 10)
			)
			(4
				(cls)
				(Print 36 3)
				(CaptainSays 36 4 #dispose)
				(= seconds 10)
			)
			(5
				(cls)
				(EgoDead 36 5)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (event claimed?))
				(or
					(== (event type?) keyDown)
					(== (event type?) mouseDown)
				)
				modelessDialog
			)
			(event claimed: TRUE)
			(= cycles (= seconds 0))
			(self cue:)
		)
	)
)

(instance speak of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: Forward)
				(= seconds (Random 1 2))
			)
			(1
				(client setCycle: EndLoop self)
			)
			(2
				(= seconds (Random 1 2))
				(= state -1)
			)
		)
	)
)

(instance capFace of View)

(instance capTorso of View)

(instance capHand of View)

(instance capLips of Prop)

(instance sonny of View)
