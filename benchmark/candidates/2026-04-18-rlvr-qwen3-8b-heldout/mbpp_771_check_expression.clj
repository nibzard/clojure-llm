(defn check_expression
  "	Write a function to check if the given expression is balanced or not. https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/"
  [exp]
  (let [s (or exp "")
        pairs {\) \( \} \{ \] \[}
        open? (set (vals pairs))]
    (loop [chars (seq s)
           stack []]
      (cond
        (nil? chars) (empty? stack)
        (contains? open? (first chars)) (recur (next chars) (conj stack (first chars)))
        (contains? pairs (first chars)) (if (and (seq stack)
                                                 (= (first chars) (pairs (peek stack))))
                                            (recur (next chars) (pop stack))
                                            false)
        :else (recur (next chars) stack)))))