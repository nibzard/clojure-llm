(defn iscube
  [a]
  (let [root (Math/round (Math/cbrt a))]
    (= a (* root root root))))