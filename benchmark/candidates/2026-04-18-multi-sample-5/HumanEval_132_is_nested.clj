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
            opens 0
            pairs 0]
       (if-let [ch (first chars)]
         (case ch
           \[ (if (pos? pairs)
                true
                (recur (next chars) (inc opens) pairs))
           \] (if (pos? opens)
                (recur (next chars) (dec opens) (inc pairs))
                (recur (next chars) opens pairs)))
         false))))))