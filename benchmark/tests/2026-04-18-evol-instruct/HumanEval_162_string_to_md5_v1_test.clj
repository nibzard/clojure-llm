(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= nil (vector_to_sha1 [])))
  (is (= "9ef50cc82ae474279fb8e82896142702bccbb33a" (vector_to_sha1 [1 2 3]))))

(run-test test-variation)
