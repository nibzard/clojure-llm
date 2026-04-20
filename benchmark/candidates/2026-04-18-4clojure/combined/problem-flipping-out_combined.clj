(defn problem-flipping-out [a]
  (fn [& args]
    (apply a (reverse args))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-flipping-out
  (is (= (= 3 ((problem-flipping-out nth) 2 [1 2 3 4 5]))))
  (is (= (= true ((problem-flipping-out >) 7 8))))
  (is (= (= 4 ((problem-flipping-out quot) 2 8))))
  (is (= (= [1 2 3] ((problem-flipping-out take) [1 2 3 4 5] 3)))))

(run-tests)
