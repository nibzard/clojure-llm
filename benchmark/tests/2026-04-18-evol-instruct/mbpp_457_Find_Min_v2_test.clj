(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 3 2] (find-shortest-subseq [1 2 1 3 2])))
  (is (= [:a :c :b :c] (find-shortest-subseq [:a :b :a :c :b :c])))
  (is (nil? (find-shortest-subseq []))))

(run-test test-variation)
