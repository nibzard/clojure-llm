(defn correct_bracketing
  "brackets is a string of \"<\" and \">\".
  return true if every opening bracket has a corresponding closing bracket."
  [brackets]
  (let [result (reduce (fn [count char]
                         (if (< count 0)
                           (reduced -1)
                           (case char
                             \< (inc count)
                             \> (dec count)
                             count)))
                       0
                       brackets)]
    (= result 0)))