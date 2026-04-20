(defn check_expression
  "	Write a function to check if the given expression is balanced or not. https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/"
  [exp]
  (let [pairs {\) \( \} \{ \] \[}
        opening? (set (vals pairs))
        closing? (set (keys pairs))
        balance (fn [s]
                  (loop [chars (seq s)
                         stack '()]
                    (if-let [ch (first chars)]
                      (cond
                        (opening? ch) (recur (next chars) (cons ch stack))
                        (closing? ch)
                        (if (= (pairs ch) (first stack))
                          (recur (next chars) (rest stack))
                          false)
                        :else (recur (next chars) stack))
                      (nil? stack))))]
    (balance (or exp ""))))