(defn problem-power-set [a]
  (reduce (fn [ps x]
            (into ps (map #(conj % x) ps)))
          #{#{}}
          a))