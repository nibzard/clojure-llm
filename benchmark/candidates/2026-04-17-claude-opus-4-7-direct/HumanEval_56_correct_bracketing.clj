(defn correct-bracketing
  "brackets is a string of \"<\" and \">\".
  return true if every opening bracket has a corresponding closing bracket."
  [brackets]
  (loop [depth 0
         [c & rest] brackets]
    (cond
      (nil? c) (zero? depth)
      (= c \<) (recur (inc depth) rest)
      (= c \>) (if (pos? depth)
                 (recur (dec depth) rest)
                 false)
      :else (recur depth rest))))