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
  (loop [chars (seq brackets)
         balance 0]
    (cond
      (nil? chars) (zero? balance)
      (= (first chars) \<) (recur (next chars) (inc balance))
      (= (first chars) \>) (if (pos? balance)
                             (recur (next chars) (dec balance))
                             false)
      :else (recur (next chars) balance))))