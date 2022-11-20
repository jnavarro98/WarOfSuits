About the gameplay and design of cards:
- I decided to make a single screen with controls inverted so players can play
like a real game.
- Suit priority is shown with stars around the card.
- Cards are custom components, all made with vectors for performance and to adapt to
  evey screen.

About the technologies used:
I decided to use MVVM with data sources and live data. It is a good architecture to keep
SOLID principles. This combined with Live Data makes a powerful, easy to read and to
maintain architecture.

About the extra part:
I wish I could've done it but I really wanted to implement UI tests and had some
problems with the integration (more on readme in androidTests) and spent much time
trying to fix them.
I rather have a well design project than one full of features but not well tested.
If I had time to implement them I would've saved each turn in the finishTurn() function,
make a list of TurnRecords and then display those at the end of the game with a design
pattern similar to WelcomeActivity.
Welcome activity may seem overkill for having only one button, but it was taken from
another project I've done in the past and would have been nice to use it for turn history.

As a final thought, I really enjoyed doing this project, I did it for fun as I always do
when coding. I got to love it and I took care in the design, with animations and vectors
so it looks like a professional game.