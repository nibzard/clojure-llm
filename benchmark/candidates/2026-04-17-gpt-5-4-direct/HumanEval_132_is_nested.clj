(defn is_nested
  "Create a function that takes a string as input which contains only square brackets.
  The function should return true if and only if there is a valid subsequence of brackets 
  where at least one bracket in the subsequence is nested.
  >>> (is_nested \"[[]]\")
  true
  >>> (is_nested \"[]]]]]]][[[[[]\")
  false
  >>> (is_nested \"[][]\")
  false
  >>> (is_nested \"[]\")
  false
  >>> (is_nested \"[[][]]\")
  true
  >>> (is_nested \"[[]][[\")
  true"
  [string]
  (boolean
   (when (seq string)
     (loop [chars (seq string)
            balance 0
            seen-open false]
       (if-let [ch (first chars)]
         (cond
           (= ch \[)
           (if seen-open
             true
             (recur (next chars) (inc balance) (pos? (inc balance))))

           (= ch \])
           (recur (next chars) (max 0 (dec balance)) (pos? (max 0 (dec balance))))

           :else
           (recur (next chars) balance seen-open))
         false)))))