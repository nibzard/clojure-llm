(defn problem-transitive-closure [a]
  (loop [rel a]
    (let [new-rel
          (into rel
                (for [[x y] rel
                      [u v] rel
                      :when (= y u)]
                  [x v]))]
      (if (= new-rel rel)
        rel
        (recur new-rel)))))