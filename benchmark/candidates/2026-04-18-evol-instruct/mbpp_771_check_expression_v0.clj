(defn balanced-delims?
  "Return true if the expression contains balanced (), [], and {} delimiters.
Examples:
  (balanced-delims? \"([{}])\") => true
  (balanced-delims? \"([)]\") => false
  (balanced-delims? nil) => true"
  [exp]
  (let [pairs {\) \( \] \[ \} \{}
        opens (set (vals pairs))]
    (loop [chars (seq (or exp ""))
           stack []]
      (if (empty? chars)
        (empty? stack)
        (let [c (first chars)]
          (cond
            (opens c) (recur (rest chars) (conj stack c))
            (contains? pairs c)
            (if (= (peek stack) (pairs c))
              (recur (rest chars) (pop stack))
              false)
            :else (recur (rest chars) stack)))))))