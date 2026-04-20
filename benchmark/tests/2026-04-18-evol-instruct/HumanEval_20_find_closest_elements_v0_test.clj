(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 3 2 1] (longest-palindromic-subvector [1 2 3 2 1 4])))
  (is (= [:a :b :b :a] (longest-palindromic-subvector [:a :b :b :a :c])))
  (is (= [1] (longest-palindromic-subvector [1 2 3])))
  (is (= [] (longest-palindromic-subvector []))))

(run-test test-variation)
