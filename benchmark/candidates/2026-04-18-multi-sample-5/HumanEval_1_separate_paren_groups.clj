(defn separate_paren_groups
  " Input to this function is a string containing multiple groups of nested parentheses. Your goal is to
  separate those group into separate strings and return the list of those.
  Separate groups are balanced (each open brace is properly closed) and not nested within each other
  Ignore any spaces in the input string.
  >>> (separate_paren_groups \"( ) (( )) (( )( ))\")
  [\"()\" \"(())\" \"(()())\"]"
  [paren_string]
  (let [chars (remove #{\space} (or paren_string ""))]
    (loop [cs chars
           depth 0
           current []
           groups []]
      (if-let [c (first cs)]
        (let [new-depth (case c
                          \( (inc depth)
                          \) (dec depth)
                          depth)
              new-current (conj current c)]
          (if (zero? new-depth)
            (recur (rest cs) new-depth [] (conj groups (apply str new-current)))
            (recur (rest cs) new-depth new-current groups)))
        groups))))