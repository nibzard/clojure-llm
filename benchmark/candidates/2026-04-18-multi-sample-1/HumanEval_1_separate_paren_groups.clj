(defn separate_paren_groups
  " Input to this function is a string containing multiple groups of nested parentheses. Your goal is to
  separate those group into separate strings and return the list of those.
  Separate groups are balanced (each open brace is properly closed) and not nested within each other
  Ignore any spaces in the input string.
  >>> (separate_paren_groups "( ) (( )) (( )( ))")
  [\"()\" \"(())\" \"(()())\"]"
  [paren_string]
  (let [s (apply str (remove #{\space} (or paren_string "")))]
    (loop [chars (seq s)
           depth 0
           current []
           result []]
      (if-let [ch (first chars)]
        (let [new-depth (case ch
                          \( (inc depth)
                          \) (dec depth)
                          depth)
              new-current (conj current ch)]
          (if (zero? new-depth)
            (recur (next chars) 0 [] (conj result (apply str new-current)))
            (recur (next chars) new-depth new-current result)))
        result))))