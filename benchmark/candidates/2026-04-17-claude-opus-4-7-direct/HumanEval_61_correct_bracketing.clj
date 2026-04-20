(defn correct-bracketing
  "brackets is a string of \"(\" and \")\".
  return true if every opening bracket has a corresponding closing bracket."
  [brackets]
  (loop [depth 0
         [c & rest] brackets]
    (cond
      (nil? c) (zero? depth)
      (and (pos? depth) (= c \))) (recur (dec depth) rest)
      (= c \() (recur (inc depth) rest)
      :else false)))