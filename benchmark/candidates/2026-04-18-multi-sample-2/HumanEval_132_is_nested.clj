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
   (some true?
         (rest
          (reductions
           (fn [levels ch]
             (let [curr (peek levels)]
               (if (= ch \[)
                 (conj levels (inc curr))
                 (if (pos? curr)
                   (let [new-levels (update levels (dec (count levels)) dec)]
                     (if (>= curr 2)
                       (assoc new-levels 0 1)
                       new-levels))
                   levels))))
           [0]
           string)))))