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
   (second
    (reduce
     (fn [[balance nested?] ch]
       (cond
         nested? [balance true]
         (= ch \[) [(inc balance) (>= balance 1)]
         :else [(max 0 (dec balance)) false]))
     [0 false]
     string))))