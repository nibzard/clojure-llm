(defn iscube
  [a]
  (let [n (Math/round (Math/cbrt (double a)))]
    (= (* n n n) a)))