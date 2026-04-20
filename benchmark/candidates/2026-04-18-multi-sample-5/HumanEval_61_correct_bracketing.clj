(defn correct_bracketing
  " brackets is a string of \"(\" and \")\".
  return true if every opening bracket has a corresponding closing bracket.
  >>> (correct_bracketing \"(\")
  false
  >>> (correct_bracketing \"()\")
  true
  >>> (correct_bracketing \"(()())\")
  true
  >>> (correct_bracketing \")(()\")
  false"
  [brackets]
  (let [result (reduce (fn [balance ch]
                         (when (some? balance)
                           (case ch
                             \( (inc balance)
                             \) (when (pos? balance) (dec balance))
                             balance)))
                       0
                       (or brackets ""))]
    (zero? result)))