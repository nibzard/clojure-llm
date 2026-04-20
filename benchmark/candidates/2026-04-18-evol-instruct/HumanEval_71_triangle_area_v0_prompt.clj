(defn triangle-perimeter
  "Given the lengths of the three sides of a triangle, return the perimeter
  rounded to 2 decimal points if the sides form a valid triangle.
  Otherwise return -1.

  A valid triangle must satisfy: the sum of any two sides is greater than the third.

  Examples:
  >>> (triangle-perimeter 3 4 5)
  12.0
  >>> (triangle-perimeter 1 2 10)
  -1
  >>> (triangle-perimeter nil 4 5)
  -1"
  [a b c])