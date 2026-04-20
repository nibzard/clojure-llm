(defn balanced-delimiters?
  "Return true if the input string has balanced (), [], and {} delimiters.
  Ignores all non-delimiter characters.

  Examples:
  (balanced-delimiters? \"(a[b]{c})\") ;=> true
  (balanced-delimiters? \"([)]\")      ;=> false
  (balanced-delimiters? nil)           ;=> true"
  [s]
  (let [pairs {\) \( \] \[ \} \{}
        openers (set (vals pairs))
        chars (seq (or s ""))]
    (loop [cs chars, stack []]
      (if-let [ch (first cs)]
        (cond
          (openers ch) (recur (rest cs) (conj stack ch))
          (contains? pairs ch)
          (if (= (peek stack) (pairs ch))
            (recur (rest cs) (pop stack))
            false)
          :else (recur (rest cs) stack))
        (empty? stack)))))