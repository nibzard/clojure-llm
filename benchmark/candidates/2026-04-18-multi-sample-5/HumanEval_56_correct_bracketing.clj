(defn correct_bracketing
  " brackets is a string of \"<\" and \">\".
  return true if every opening bracket has a corresponding closing bracket.
  >>> (correct_bracketing \"<\")
  false
  >>> (correct_bracketing \"<>\")
  true
  >>> (correct_bracketing \"<<><>>\")
  true
  >>> (correct_bracketing \"><<>\")
  false"
  [brackets]
  (let [result (reduce (fn [balance ch]
                         (cond
                           (reduced? balance) balance
                           (= ch \<) (inc balance)
                           (= ch \>) (let [next-balance (dec balance)]
                                       (if (neg? next-balance)
                                         (reduced next-balance)
                                         next-balance))
                           :else balance))
                       0
                       (or brackets ""))]
    (and (not (reduced? result)) (zero? result))))