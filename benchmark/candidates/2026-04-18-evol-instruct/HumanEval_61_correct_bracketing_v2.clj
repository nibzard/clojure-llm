(defn balanced-delimiters
  "delims is a string containing only the characters (), [], and {}.
  Return true if the delimiters are properly balanced and nested.

  >>> (balanced-delimiters \"()\")
  true
  >>> (balanced-delimiters \"([{}])\")
  true
  >>> (balanced-delimiters \"([)]\")
  false
  >>> (balanced-delimiters \"(([\") 
  false"
  [delims]
  (let [match? {\) \( \] \[ \} \{}]
    (loop [cs (seq delims), stack '()]
      (cond
        (nil? cs) (empty? stack)
        (contains? match? (first cs))
        (let [c (first cs)]
          (if (= (peek stack) (match? c))
            (recur (next cs) (pop stack))
            false))
        :else
        (recur (next cs) (conj stack (first cs)))))))