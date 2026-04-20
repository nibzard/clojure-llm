(defn triples_sum_to_zero_sorted
  "triples_sum_to_zero_sorted takes a sorted collection of integers as input.
  It returns true if there are three distinct elements in the collection that
  sum to zero, and false otherwise.

  The input may be any sequential collection, including lazy sequences.

  Examples:
  >>> (triples_sum_to_zero_sorted [-5 -2 0 1 3 7])
  true
  >>> (triples_sum_to_zero_sorted [-4 -1 2 5 9])
  false
  >>> (triples_sum_to_zero_sorted (range -10 10))
  true
  >>> (triples_sum_to_zero_sorted [1 2 3])
  false"
  [coll])