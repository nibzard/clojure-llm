(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {1 {:id 1 :v 7}
          2 {:id 2 :v 5}
          3 {:id 3 :v 4}}
         (merge-streams-by-key :id
                               [{:id 1 :v 3} {:id 2 :v 5}]
                               [{:id 1 :v 7} {:id 3 :v 4}]))))

(run-test test-variation)
