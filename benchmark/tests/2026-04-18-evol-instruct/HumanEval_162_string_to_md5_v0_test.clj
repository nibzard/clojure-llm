(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "f7ff9e8b7bb2b91af11a6f2c2c1d6f3b6d1f5f2f" (vector_to_sha1 [72 101 108 108 111])))
  (is (= nil (vector_to_sha1 [])))
  (is (= nil (vector_to_sha1 nil))))

(run-test test-variation)
