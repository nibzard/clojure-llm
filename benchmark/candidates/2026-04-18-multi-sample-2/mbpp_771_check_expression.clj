(defn check_expression
  "	Write a function to check if the given expression is balanced or not. https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/"
  [exp]
  (let [pairs {\) \(, \] \[, \} \{}
        opens (set (vals pairs))]
    (if (nil? exp)
      true
      (loop [chs (seq exp)
             stack '()]
        (if-let [ch (first chs)]
          (cond
            (opens ch) (recur (next chs) (conj stack ch))
            (contains? pairs ch) (if (= (first stack) (pairs ch))
                                   (recur (next chs) (rest stack))
                                   false)
            :else (recur (next chs) stack))
          (empty? stack))))))