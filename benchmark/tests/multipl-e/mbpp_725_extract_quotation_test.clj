(require '[clojure.test :refer [deftest is run-test]])

(def candidate extract_quotation)

(deftest test-humaneval

  (is (= (candidate "Cortex "A53" Based "multi" tasking "Processor"") ["A53" "multi" "Processor"]))
  (is (= (candidate "Cast your "favorite" entertainment "apps"") ["favorite" "apps"]))
  (is (= (candidate "Watch content "4k Ultra HD" resolution with "HDR 10" Support") ["4k Ultra HD" "HDR 10"]))
  (is (= (candidate "Watch content '4k Ultra HD' resolution with 'HDR 10' Support") []))
)

(run-test test-humaneval)