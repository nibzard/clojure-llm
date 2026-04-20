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
           (fn [depth ch]
             (cond
               (nil? depth) nil
               (= ch \[) (inc depth)
               (= ch \]) (when (pos? depth) (dec depth))
               :else depth))
           0
           string)))))