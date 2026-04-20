(defn problem-split-by-type [a]
  (vals
   (reduce (fn [m x]
             (update m (type x) (fnil conj []) x))
           {}
           a)))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-split-by-type
  (is (= (= (set (problem-split-by-type [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})))
  (is (= (= (set (problem-split-by-type [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]})))
  (is (= (= (set (problem-split-by-type [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]}))))

(run-tests)
