(defn pattern? 
  "Return true when `patterns` describes the run-lengths of `xs`.

  A run-length pattern is a vector of positive integers indicating how many
  times each distinct consecutive value should repeat.

  Examples:
  (pattern? [1 1 2 2 2 3] [2 3 1]) ;=> true
  (pattern? [:a :a :b :b] [2 2])    ;=> true
  (pattern? [] [])                 ;=> true
  (pattern? nil [])                ;=> true

  The function must work for any sequential input, including lazy sequences."
  [xs patterns])