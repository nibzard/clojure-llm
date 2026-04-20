(defn balanced-delimiters
  "delimiters is a string containing any of the characters (), [], and {}.
  Return true if the delimiters are properly balanced and nested, false otherwise.
  Non-delimiter characters should be ignored.

  >>> (balanced-delimiters \"(a[1]{b})\")
  true
  >>> (balanced-delimiters \"([)]\")
  false
  >>> (balanced-delimiters \"foo(bar)[baz]{qux}\")
  true
  >>> (balanced-delimiters \"(\")
  false"
  [delimiters]
  (loop [chars (seq delimiters)
         stack []]
    (if-let [ch (first chars)]
      (cond
        (#{\( \[ \{} ch) (recur (rest chars) (conj stack ch))
        (#{\) \] \}} ch) (let [open (peek stack)]
                           (if (= open ({\) \( \] \[ \} \{} ch))
                             (recur (rest chars) (pop stack))
                             false))
        :else (recur (rest chars) stack))
      (empty? stack))))