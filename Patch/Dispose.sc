;;; Sierra Script 1.0 - (do not remove this comment)
(script# DISPOSESCRIPTS_SCRIPT)
(include game.sh)
(use Main)

(public
	DisposeScripts 0
)

(procedure (DisposeScripts &tmp i)
	(for ((= i 1)) (< i 989) ((++ i))
		(if (and
				(!= i curRoomNum)
				(!= i DISPOSESCRIPTS_SCRIPT)
				(!= i DSELECTOR)
				(!= i 255)
			)
		(DisposeScript i)
		)
	)
)



