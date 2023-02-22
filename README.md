# clojure-app-template
## Full stack Clojure/Clojurescript app

This app that helps teachers build lesson plans and materials using OpenAI technologies. It is currently deployed at https://lessonplanner.onrender.com/ during the early dev stage. Please note that it is using render's free tier so the first page load may be slow while it wakes up.

I have been a teacher for many years and one of the hardest, most time consuming processes us teachers encounter is lesson planning and creating the corresponding materials. I wanted a project that would allow me to explore new technologies while also solving a real world problem that I encounter on a daily basis.

This will be a full stack app backing a real business with user authentication and payment processing. This will remain public while I am in the current early stages building out the foundation for others to use (and allowing me to use it for portfolio purposes in my current job search) but I may take it private with no notice in the near future.

  ### backend: deps.edn, jetty, reitit, aero (for config)
  
  ### frontend: shadow-cljs, reagent, re-frame, tailwindcss (w/ daisy ui)
  
  ### deployed through docker 

Demo is currently deployed and running live through `render.com` at: https://lessonplanner.onrender.com/ 

It's using the free tier so first load might take a while, but when live I'm getting lighthouse scores of 97-99% Performance 
(I only get 89% here running the production release on localhost so `render.com` must be doing something nice here) 
and 100% on Accessibility, Best Practices, and SEO (but the demo is only displaying Hello World). 
The free tier allows for 512mb and this demo seems to use up to ~170mb for just the "Hello World" according to `render.com`'s metrics but I haven't looked into too much yet.

The dockerfile should allow it to be used elsewhere like `fly.io`, `railway.app`, and `heroku` but I haven't tested.

## For dev and prod: 
  `npm install`

## For dev:
### Frontend: 
`npm run dev`

HTTP server built on port `4000`

nrepl port on `3333`

I use neovim w/conjure so run your editor's equivalent of: `ConjureShadowSelect app`

This should give you hot reloading on save, including tailwind css changes.
I think something is causing postcss to run even without save sometimes, but it isn't affecting development while I track that down.

### Backend:
To start the server run `clojure -M -m lesson-planner.core`

Start your normal repl like usual. (e.g. For me that's `clj -M:repl` and connect.)

TBH, I'm not quite sure how to have the backend and frontend connected to their respective repls and running at the same time quite yet.

## For prod: 
`npm run release`

`clojure -T:build uber`

`java -jar target/lesson-planner-standalone.jar`
