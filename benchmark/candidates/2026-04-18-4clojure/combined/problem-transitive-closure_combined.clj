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

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-transitive-closure
  (is (= (let [divides #{[8 4] [9 3] [4 2] [27 9]}]
  (= (problem-transitive-closure divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]}))))
  (is (= (let [more-legs
      #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}]
  (= (problem-transitive-closure more-legs)
     #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
       ["spider" "cat"] ["spider" "man"] ["spider" "snake"]}))))
  (is (= (let [progeny
      #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}]
  (= (problem-transitive-closure progeny)
     #{["father" "son"] ["father" "grandson"]
       ["uncle" "cousin"] ["son" "grandson"]})))))

(run-tests)
