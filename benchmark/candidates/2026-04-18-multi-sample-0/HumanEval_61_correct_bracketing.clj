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
                         (cond
                           (nil? balance) nil
                           (= ch \() (inc balance)
                           (= ch \)) (when (pos? balance) (dec balance))
                           :else balance))
                       0
                       (or brackets ""))]
    (zero? (or result -1))))