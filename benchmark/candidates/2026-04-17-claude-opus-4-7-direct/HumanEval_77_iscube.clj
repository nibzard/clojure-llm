(defn iscube
  [a]
  (let [c (int (Math/round (Math/clojure.math/pow (Math/abs a) (/ 1.0 3.0))))]
    (= (* c c c) (Math/abs a))))