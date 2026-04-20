(defn check_expression
  "	Write a function to check if the given expression is balanced or not. https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/"
  [exp]
  (let [opening? (set "({[")
        closing? (set ")}]")
        pairs {\) \(, \} \{, \] \[}
        reduce (reduce (fn [[stack balanced?] ch]
                         (cond
                           (contains? opening? ch) [(conj stack ch) balanced?]
                           (contains? closing? ch) (let [expected (get pairs ch)]
                                                  (if (and (seq stack) (= (peek stack) expected))
                                                    [(pop stack) balanced?]
                                                    [stack false]))
                           :else [stack balanced?]))
                       [[] true]
                       (or exp "")]
        [final-stack balanced?])
    (= balanced? true)))